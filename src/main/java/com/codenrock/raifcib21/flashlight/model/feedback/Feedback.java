package com.codenrock.raifcib21.flashlight.model.feedback;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Feedback {
    private UUID id;
    private FeedbackSourceType sourceType;
    private boolean liked;
    private boolean disliked;
    private String text;
}
