package com.valerianosoft.mutanttest.model;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceGroup {

  private Set<UUID> ids;
  private BigDecimal totalGrossValue;
  private BigDecimal totalDiscount;
  private BigDecimal totalFinalValue;
  private BigDecimal totalIcms;
  private BigDecimal totalIss;
  private BigDecimal totalPis;
  private boolean taxed;
  private boolean taxedOfPis;
  private boolean taxedOfIss;
  private boolean biggerThanExpected;

}
