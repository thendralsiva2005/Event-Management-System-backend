package com.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EventRequest {

    @NotNull(message = "userId is required for create event")
    private Integer userId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer eventId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer bookingId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    private LocalDate eventDate;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than zero")
    private Integer capacity;

    @NotNull(message = "Registration deadline is required")
    @Future(message = "Registration deadline must be in the future")
    private LocalDate registrationDeadline;
}