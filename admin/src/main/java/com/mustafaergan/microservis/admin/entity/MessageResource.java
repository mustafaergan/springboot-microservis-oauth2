package com.mustafaergan.microservis.admin.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MESSAGE_RESOURCE")
@Data
public class MessageResource implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "MESSAGE_KEY", nullable = false)
    private String messageKey;

    @Column(name = "TR")
    private String tr;

    @Column(name = "EN")
    private String en;

    @Column(name = "FR")
    private String fr;

    @Column(name = "DE")
    private String de;
}
