package com.boissinot.jenkins.csvexporter.integration;

import com.boissinot.jenkins.csvexporter.domain.InputSBJobObj;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;

import static com.boissinot.jenkins.csvexporter.domain.JobMessageHeaders.*;

/**
 * @author Gregory Boissinot
 */
public class JobMessageBuilder {

    @SuppressWarnings("unused")
    public Message buildJobMessage(Message message) {
        InputSBJobObj jobObj = (InputSBJobObj) message.getPayload();
        System.out.println(String.format("Processing '%s'", jobObj.getJobName()));
        return MessageBuilder
                .withPayload(jobObj.getConfigXML())
                .copyHeaders(message.getHeaders())
                .setHeader(HEADER_JOB_NAME, jobObj.getJobName())
                .setHeader(HEADER_FUNCTIONAL_JOB_TYPE, jobObj.getFunctionalJobType())
                .setHeader(HEADER_FUNCTIONAL_JOB_LANGUAGE, jobObj.getFunctionalJobLanguage())
                .setHeader(HEADER_FUNCTIONAL_MODULE_MAP, jobObj.getContextMap())
                .build();
    }
}
