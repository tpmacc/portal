package tpmacc.notes.domain;

import javax.validation.constraints.NotBlank;

public record Note (
        @NotBlank(message = "Note title must not be blank")
        String title,
        String content
){}
