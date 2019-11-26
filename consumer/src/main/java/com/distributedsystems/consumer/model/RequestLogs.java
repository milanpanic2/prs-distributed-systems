package com.distributedsystems.consumer.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "logs")
@NoArgsConstructor
@AllArgsConstructor
public class RequestLogs {
    @Id
    String time;
    String message;
}
