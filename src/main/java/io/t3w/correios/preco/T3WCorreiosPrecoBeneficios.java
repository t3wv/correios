package io.t3w.correios.preco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.preco.enums.Abrangencia;
import io.t3w.correios.preco.enums.TipoBeneficio;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrecoBeneficios {
    @JsonProperty("codigo")
    private String codigo;
    @JsonProperty("tipoBeneficio")
    private TipoBeneficio beneficio;
    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty("abrangencia")
    private Abrangencia abrangencia;
    @JsonProperty("coPacote")
    private String codigoPacote;
    @JsonProperty("noPacote")
    private String nomePacote;
    @JsonProperty("incondicional")
    private boolean incondicional;
    @JsonProperty("criterio")
    private List<T3WCorreiosPrecoCriterioFuncional> criterioFuncionalList;
    @JsonProperty("vlBaseCalculo")
    private double valorBaseCalculoImposto;
    @JsonProperty("percentual")
    private double percentual;
    @JsonProperty("valor")
    private double valor;

    public T3WCorreiosPrecoBeneficios() {}

    public T3WCorreiosPrecoBeneficios(String codigo, TipoBeneficio beneficio, String tipo, Abrangencia abrangencia, String codigoPacote, String nomePacote, boolean incondicional, List<T3WCorreiosPrecoCriterioFuncional> criterioFuncionalList, double valorBaseCalculoImposto, double percentual, double valor) {
        this.codigo = codigo;
        this.beneficio = beneficio;
        this.tipo = tipo;
        this.abrangencia = abrangencia;
        this.codigoPacote = codigoPacote;
        this.nomePacote = nomePacote;
        this.incondicional = incondicional;
        this.criterioFuncionalList = criterioFuncionalList;
        this.valorBaseCalculoImposto = valorBaseCalculoImposto;
        this.percentual = percentual;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosPrecoBeneficios setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public TipoBeneficio getBeneficio() {
        return beneficio;
    }

    public T3WCorreiosPrecoBeneficios setBeneficio(TipoBeneficio beneficio) {
        this.beneficio = beneficio;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public T3WCorreiosPrecoBeneficios setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public Abrangencia getAbrangencia() {
        return abrangencia;
    }

    public T3WCorreiosPrecoBeneficios setAbrangencia(Abrangencia abrangencia) {
        this.abrangencia = abrangencia;
        return this;
    }

    public String getCodigoPacote() {
        return codigoPacote;
    }

    public T3WCorreiosPrecoBeneficios setCodigoPacote(String codigoPacote) {
        this.codigoPacote = codigoPacote;
        return this;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public T3WCorreiosPrecoBeneficios setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
        return this;
    }

    public boolean isIncondicional() {
        return incondicional;
    }

    public T3WCorreiosPrecoBeneficios setIncondicional(boolean incondicional) {
        this.incondicional = incondicional;
        return this;
    }

    public List<T3WCorreiosPrecoCriterioFuncional> getCriterioFuncionalList() {
        return criterioFuncionalList;
    }

    public T3WCorreiosPrecoBeneficios setCriterioFuncionalList(List<T3WCorreiosPrecoCriterioFuncional> criterioFuncionalList) {
        this.criterioFuncionalList = criterioFuncionalList;
        return this;
    }

    public double getValorBaseCalculoImposto() {
        return valorBaseCalculoImposto;
    }

    public T3WCorreiosPrecoBeneficios setValorBaseCalculoImposto(double valorBaseCalculoImposto) {
        this.valorBaseCalculoImposto = valorBaseCalculoImposto;
        return this;
    }

    public double getPercentual() {
        return percentual;
    }

    public T3WCorreiosPrecoBeneficios setPercentual(double percentual) {
        this.percentual = percentual;
        return this;
    }

    public double getValor() {
        return valor;
    }

    public T3WCorreiosPrecoBeneficios setValor(double valor) {
        this.valor = valor;
        return this;
    }
}
