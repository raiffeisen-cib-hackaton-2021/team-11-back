package com.codenrock.raifcib21.flashlight.controller.feedback;

import com.codenrock.raifcib21.flashlight.model.feedback.Feedback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {

    @PostMapping
    public UUID post(Feedback feedback) {
        return null;
    }
}
