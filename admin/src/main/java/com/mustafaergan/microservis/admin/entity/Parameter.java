package com.mustafaergan.microservis.admin.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PARAMETER")
@Data
@ToString(of = {"id", "param", "value", "description"})
public class Parameter implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="PARAM", nullable = false)
    private String param;

    @Column(name="VALUE", nullable = false)
    private String value;

    @Column(name="DESCRIPTION")
    private String description;
}
