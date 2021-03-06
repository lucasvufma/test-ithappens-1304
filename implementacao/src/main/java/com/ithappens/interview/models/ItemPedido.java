package com.ithappens.interview.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ithappens.interview.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    private Status status = Status.ATIVO;

    private Double custoUnitario;

    private Double subTotal;

    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    @JsonIgnoreProperties("itemPedido")
    private Produto produto;

    public double calculaSubTotal() {
        return this.quantidade * this.custoUnitario;
    }

}
