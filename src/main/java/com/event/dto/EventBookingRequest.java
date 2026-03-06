package com.event.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventBookingRequest {

   @NotNull
   private Integer userId;
   @NotNull
   private Integer eventId;
}
