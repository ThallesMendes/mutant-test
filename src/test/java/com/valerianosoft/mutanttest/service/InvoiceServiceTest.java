package com.valerianosoft.mutanttest.service;

import static org.junit.jupiter.api.Assertions.*;

import com.valerianosoft.mutanttest.model.Invoice;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoiceServiceTest {

  private InvoiceService service;

  @BeforeEach
  void setUp() {
    this.service = new InvoiceService();
  }

  @Test
  void calculateInvoices() {
    final var invoiceOne = Invoice.builder()
        .id(UUID.randomUUID())
        .grossValue(BigDecimal.valueOf(150.50))
        .discount(BigDecimal.valueOf(25.40))
        .taxExempt(false)
        .icms(BigDecimal.valueOf(10.0))
        .iss(BigDecimal.valueOf(15.0))
        .pis(BigDecimal.valueOf(5.0))
        .build();

    final var invoiceTwo = Invoice.builder()
        .id(UUID.randomUUID())
        .grossValue(BigDecimal.valueOf(130.50))
        .discount(BigDecimal.valueOf(10.40))
        .taxExempt(true)
        .icms(BigDecimal.valueOf(30.0))
        .iss(BigDecimal.valueOf(15.0))
        .pis(BigDecimal.valueOf(5.0))
        .build();

    final var group = this.service.calculateInvoices(List.of(invoiceOne, invoiceTwo));

    assertNotNull(group);
    assertEquals(BigDecimal.valueOf(281.0), group.getTotalGrossValue());
    assertEquals(BigDecimal.valueOf(35.80), group.getTotalDiscount());
    assertEquals(BigDecimal.valueOf(245.20), group.getTotalFinalValue());
    assertEquals(BigDecimal.valueOf(10.0), group.getTotalIcms());
    assertEquals(BigDecimal.valueOf(30.0), group.getTotalIss());
    assertEquals(BigDecimal.valueOf(10.0), group.getTotalPis());
    assertTrue(group.isTaxed());
    assertTrue(group.isTaxedOfIss());
    assertTrue(group.isTaxedOfPis());
    assertFalse(group.isBiggerThanExpected());

  }
}