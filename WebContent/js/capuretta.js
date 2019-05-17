function apagaTexto(elemento)
{
    elemento.value = "";
}

function proxOrcamento()
{
    var x = document.getElementById("firstname").value;
    if(x.includes("porra"))
    {
        window.open("sobre.html", "_blank");
    }
    else
    {
        window.open("orcamento1.html", "_self");
    }
}

var contador = 0;

function adicionaPartes()
{
    var texto = "Serviço:<br><select style=`width: 450px` name=`select`><option value=`valor0` disabled selected>Selecione uma opção</option> <option value=`valor1`>Produção musical</option> <option value=`valor2`>Gravação</option><option value=`valor3`>Mixagem e masterização</option><option value=`valor3`>Produção de vídeo</option></select><br><p></p>Quantidade de horas:<br><select style=`width: 450px` name=`select`><option value=`valor0` disabled selected>Selecione a quantidade de horas</option> <option value=`valor1`>Período matutino de 5h</option> <option value=`valor2`>Período vespertino de 5h</option><option value=`valor3`>Período de 12h</option></select><br><br>";
    if(contador == 0)
    {
        document.getElementById("parte").innerHTML = texto;
        contador++;
    }
    else if(contador == 1)
    {
        document.getElementById("parte1").innerHTML = texto;
        contador++;
    }
    else if(contador == 2)
    {
        document.getElementById("parte2").innerHTML = texto;
        contador++;
    }
}
