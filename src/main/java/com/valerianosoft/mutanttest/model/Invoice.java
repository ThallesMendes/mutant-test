package com.valerianosoft.mutanttest.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {

  private UUID id;
  private BigDecimal grossValue;
  private BigDecimal discount;
  private BigDecimal finalValue;
  private boolean taxExempt;
  private BigDecimal icms;
  private BigDecimal iss;
  private BigDecimal pis;

}
