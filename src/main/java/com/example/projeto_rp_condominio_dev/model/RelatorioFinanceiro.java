package com.example.projeto_rp_condominio_dev.model;

public class RelatorioFinanceiro {

    private Double orcamento, gastoTotal, diferenca;

    private Habitante moradorMaiorCusto;

    public RelatorioFinanceiro(Double orcamento, Double gastoTotal, Double diferenca, Habitante moradorMaiorCusto) {
        this.orcamento = orcamento;
        this.gastoTotal = gastoTotal;
        this.diferenca = diferenca;
        this.moradorMaiorCusto = moradorMaiorCusto;
    }
}
