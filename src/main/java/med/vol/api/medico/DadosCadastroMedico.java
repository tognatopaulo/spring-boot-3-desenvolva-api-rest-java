package med.vol.api.medico;

import med.vol.api.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String CRM, Especialidade especialidade, DadosEndereco endereco) {
}
