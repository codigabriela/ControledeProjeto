
const formulario = document.getElementById("formProjeto");
const mensagem = document.getElementById("mensagem");
const listaProjetos = document.getElementById("listaProjetos");

// Buscar e mostrar os projetos cadastrados
async function listarProjetos() {
    try {
        const resposta = await fetch("/api/projetos");

        if (!resposta.ok) {
            throw new Error("Não foi possível buscar os projetos.");
        }

        const projetos = await resposta.json();

        listaProjetos.innerHTML = "";

        projetos.forEach(projeto => {
            const item = document.createElement("div");
            item.className = "projeto";

            item.innerHTML = `
                <h3>${projeto.descricao}</h3>
                <p><strong>ID:</strong> ${projeto.id}</p>
                <p><strong>Início:</strong> ${projeto.dataInicio}</p>
                <p><strong>Término:</strong> ${projeto.dataFim}</p>
            `;

            listaProjetos.appendChild(item);
        });

    } catch (erro) {
        mensagem.textContent = erro.message;
    }
}

// Cadastrar um novo projeto
formulario.addEventListener("submit", async function(evento) {
    evento.preventDefault();

    const projeto = {
        descricao: document.getElementById("descricao").value,
        dataInicio: document.getElementById("dataInicio").value,
        dataFim: document.getElementById("dataFim").value
    };

    try {
        const resposta = await fetch("/api/projetos", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(projeto)
        });

        const resultado = await resposta.json();

        if (!resposta.ok) {
            throw new Error(resultado.erro || "Erro ao cadastrar projeto.");
        }

        mensagem.textContent = "Projeto cadastrado com sucesso!";
        formulario.reset();

        listarProjetos();

    } catch (erro) {
        mensagem.textContent = erro.message;
    }
});

// Carregar os projetos quando abrir a tela
listarProjetos();
