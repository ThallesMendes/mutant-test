package com.valerianosoft.mutanttest.service;

import com.valerianosoft.mutanttest.model.Invoice;
import com.valerianosoft.mutanttest.model.InvoiceGroup;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvoiceService {

  public InvoiceGroup calculateInvoices(final List<Invoice> invoices) {

    final var group = new InvoiceGroup();

    invoices.forEach(invoice -> invoice.setFinalValue(invoice.getGrossValue().subtract(invoice.getDiscount())));

    group.setIds(invoices.stream().map(Invoice::getId).collect(Collectors.toSet()));
    group.setTotalGrossValue(this.sumList(invoices.stream().map(Invoice::getGrossValue)));
    group.setTotalDiscount(this.sumList(invoices.stream().map(Invoice::getDiscount)));
    group.setTotalFinalValue(this.sumList(invoices.stream().map(Invoice::getFinalValue)));
    group.setTotalIcms(this.sumList(invoices.stream().filter(i -> !i.isTaxExempt()).map(Invoice::getIcms)));
    group.setTotalIss(this.sumList(invoices.stream().map(Invoice::getIss)));
    group.setTotalPis(this.sumList(invoices.stream().map(Invoice::getPis)));

    if (group.getTotalIcms().compareTo(BigDecimal.ZERO) > 0) {
      group.setTaxed(true);
    }

    if (group.getTotalIss().compareTo(BigDecimal.ZERO) > 0) {
      group.setTaxedOfIss(true);
    }

    if (group.getTotalPis().compareTo(BigDecimal.ZERO) > 0) {
      group.setTaxedOfPis(true);
    }

    final var sumTaxed = group.getTotalIcms()
        .add(group.getTotalIss())
        .add(group.getTotalPis());

    if (sumTaxed.compareTo(BigDecimal.valueOf(100.0)) > 0) {
      group.setBiggerThanExpected(true);
    }

    return group;

  }

  private BigDecimal sumList(Stream<BigDecimal> values) {
    return values.reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
