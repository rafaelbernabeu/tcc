package models.enums;

/**
 * Created by Rafael on 26/09/2015.
 */
public enum Estado {

    Acre("Acre","AC"), Alagoas("Alagoas", "AL"), Amapa("Amapá", "AP"),
    Amazonas("Amazonas", "AM"), Bahia("Bahia", "BA"), Ceara("Ceará", "CE"), Distrito_Federal("Distrito Federal", "DF"),
    Espirito_Santo("Espirito Santo", "ES"), Goias("Goiás", "GO"), Maranhao("Maranhão", "MA"), Mato_Grosso("Mato Grosso", "MT"),
    Mato_Grosso_do_Sul("Mato Grosso do Sul", "MS"), Minas_Gerais("Minas Gerais", "MG"),
    Para("Pará", "PA"), Paraiba("Paraíba", "PB"), Parana("Paraná", "PR"), Pernambuco("Pernambuco", "PE"), Piaui("Piauí", "PI"),
    Rio_de_Janeiro("Rio de Janeiro", "RJ"), Rio_Grande_do_Norte("Rio Grande do Norte", "RN"),
    Rio_Grande_do_Sul("Rio Grande do Sul", "RS"), Rondonia("Rondonia", "RO"), Roraima("Roraima", "RR"),
    Santa_Catarina("Santa Catarina", "SC"), Sao_Paulo("São Paulo", "SP"), Sergipe("Sergipe", "SE"), Tocantins("Tocantins", "TO");

    private String descricao;
    private String sigla;

    Estado(String descricao, String sigla) {
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }
    public String getSigla() { return sigla; }

    public static Estado getEstadoByDescricao(String descricao) {
        for (Estado estado : Estado.values()) {
            if (estado.getDescricao().equals(descricao)) {
                return estado;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
