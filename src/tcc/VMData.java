/**
 * @author mauriverti
 */
package tcc;

import java.io.Serializable;

public class VMData implements Serializable {

    private String domain;
    private Integer cpuOcupada;
    private Integer memUsada;
    private String memLivre;

    public Integer getCpuOcupada() {
        return cpuOcupada;
    }

    public void setCpuOcupada(Integer cpuOcupada) {
        this.cpuOcupada = cpuOcupada;
    }

    public Integer getMemUsada() {
        return memUsada;
    }

    public void setMemUsada(Integer memUsada) {
        this.memUsada = memUsada;
    }

    public String getMemLivre() {
        return memLivre;
    }

    public void setMemLivre(String memLivre) {
        this.memLivre = memLivre;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dominio: ");
        sb.append(getDomain());
        sb.append(" | CPU Livre: ");
        sb.append(getCpuOcupada());
        sb.append(" | Memo Usada: ");
        sb.append(getMemUsada());
        sb.append(" | Memo Livre: ");
        sb.append(getMemLivre());

        return sb.toString();
    }
}
