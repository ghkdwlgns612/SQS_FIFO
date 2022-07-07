package com.jihun;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.UUID;

public class Producer {

    private static final String QUEUE_NAME = "luman";
    private static final String ACCESS_KEY = "A~";
    private static final String SECRET_KEY = "k~";

    public static void sendMessage() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        final AmazonSQS sqs = AmazonSQSClient.builder()
                .withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        System.out.println("SQS Credential 및 지역 설정완료");

        String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl() + ".fifo";
        System.out.println("url : " + queueUrl);
        SendMessageRequest requestOne = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId(UUID.randomUUID().toString())
                .withMessageBody("send One+");
        sqs.sendMessage(requestOne);

        System.out.println("단일 요청 완료");
        SendMessageBatchRequest requestMulti = new SendMessageBatchRequest()
                .withQueueUrl(queueUrl)
                .withEntries(
                        new SendMessageBatchRequestEntry("msg1","send Multi First+")
                                .withMessageGroupId(UUID.randomUUID().toString()),
                        new SendMessageBatchRequestEntry("msg2", "send Multi Second+")
                                .withMessageGroupId(UUID.randomUUID().toString())
                );
        sqs.sendMessageBatch(requestMulti);
        System.out.println("배치 요청 완료");
    }
}
