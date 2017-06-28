package com.flow.platform.cc.test.controller;

import com.flow.platform.cc.test.TestBase;
import com.flow.platform.domain.CmdResult;
import com.flow.platform.domain.Jsonable;
import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gy@fir.im on 28/06/2017.
 * Copyright fir.im
 */
public class CmdResultControllerTest extends TestBase {

    @Test
    public void should_list_cmd_result() throws Throwable {
        // given:
        CmdResult result = new CmdResult();

        Map<String, String> output = new HashMap<>();
        output.put("FLOW_DAO_TEST", "hello");
        output.put("FLOW_DAO_TEST_1", "aa");

        result.setCmdId(UUID.randomUUID().toString());
        result.setExitValue(1);
        result.setDuration(10L);
        result.setStartTime(ZonedDateTime.now());
        result.setFinishTime(ZonedDateTime.now());
        result.setExecutedTime(ZonedDateTime.now());
        result.setProcessId(1013);
        result.setTotalDuration(10L);
        result.setOutput(output);
        result.setExceptions(Lists.newArrayList(
                new RuntimeException("Dummy Exception"), new RuntimeException("Dummy Exception")));

        cmdResultDao.save(result);

        // when:
        MockHttpServletRequestBuilder content = post("/cmd/result/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Jsonable.GSON_CONFIG.toJson(Lists.newArrayList(result.getCmdId())));

        MvcResult mvcResult = this.mockMvc.perform(content)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // then:
        String json = mvcResult.getResponse().getContentAsString();

        TypeToken<List<CmdResult>> typeToken = new TypeToken<List<CmdResult>>() {};
        List<CmdResult> cmdResultList = Jsonable.GSON_CONFIG.fromJson(json, typeToken.getType());
        Assert.assertNotNull(cmdResultList);
        Assert.assertEquals(result, cmdResultList.get(0));
    }
}
