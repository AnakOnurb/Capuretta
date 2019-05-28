var orcUser = "";
var orcEmail = "";
var orcTelefone = "";
var orcuserID = 0;
var lastInserted = 0;
var contador = 0;
var serviceList = [];

function ServicoObj(id, descricao, precohora) {
   this.id = id;
   this.descricao = descricao;
   this.precohora = precohora;
}

function apagaTexto(elemento)
{
    elemento.value = "";
}

function proxOrcamento()
{
    orcUser = "";
    orcEmail = "";
    orcEmail = "";

    var x = document.getElementById("firstname").value;
    if(x.includes("quem sabe"))
    {
        window.open("sobre.html", "_blank");
    }
    else
    {
        var nome = "";
        nome = document.getElementById("firstname").value;
        var email= "";
        email = document.getElementById("email").value;
        var telefone = "";
        telefone = document.getElementById("telefone").value;
        if(nome != "" && email != "" && email.includes("@") && telefone != "")
        {
            orcUser = nome;
            orcEmail = email;
            orcTelefone = telefone;
            verifyUser(nome, email, telefone);
            window.open("orcamento1.html?nome="+nome+"&email="+email+"&telefone="+telefone, "_self");
        }
        else
        {
            alert("Por favor, preencha todas as informações!");
            document.getElementById("firstname").focus();
        }
    }
}

function continuarOrcamento()
{
  let url = new URL(location.href);
  let searchParams = new URLSearchParams(url.search);
  orcUser = searchParams.get('nome');
  orcEmail = searchParams.get('email');
  orcTelefone = searchParams.get('telefone');
}

function verifyUser(nome, email, telefone)
{
    url = "http://localhost:8079/Capuretta/UsuarioAPI";
    console.log(url);
    var xhr = new XMLHttpRequest();
    xhr.onload = function() {
      if (xhr.status >= 200 && xhr.status < 300) {
        console.log(xhr.responseText);
        console.log('Success!', JSON.parse(xhr.responseText));
        var response = JSON.parse(xhr.responseText);
        var existe = false;

        for(var item of response.Usuario)
        {
          console.log(item);
          if(item.nome == nome && item.email == email)
          {
              console.log("usuário existe");
              existe = true;
              orcuserID = item.id;
          }
        }
        console.log(existe);
        if(existe == false)
        {
            AddUser(nome, email, telefone);
        }
      }
      else {
        let error_message= "Error <br>" + xhr.statusText;
        console.log(error_message);
      }
    };
    xhr.open('GET', url, false);
    xhr.send();
}

function AddUser(nome, email, telefone)
{
    url = "http://localhost:8079/Capuretta/UsuarioAPI?nome="+nome+"&email="+email+"&telefone="+telefone;
    console.log(url);
    var xhr = new XMLHttpRequest();
    xhr.onload = function() {
      if (xhr.status >= 200 && xhr.status < 300) {
        console.log('Success!', JSON.parse(xhr.responseText));
      }
      else {
        let error_message= "Error <br>" + xhr.statusText;
        console.log(error_message);
      }
      console.log("Pages Retrieved");
    };
    xhr.open('POST', url, false);
    xhr.send();
}

function adicionaPartes()
{
    // var texto = "Serviço:<br><select style=`width: 450px` name=`select`><option value=`valor0` disabled selected>Selecione uma opção</option> <option value=`valor1`>Produção musical</option> <option value=`valor2`>Gravação</option><option value=`valor3`>Mixagem e masterização</option><option value=`valor3`>Produção de vídeo</option></select><br><p></p>Quantidade de horas:<br><select style=`width: 450px` name=`select`><option value=`valor0` disabled selected>Selecione a quantidade de horas</option> <option value=`valor1`>Período matutino de 5h</option> <option value=`valor2`>Período vespertino de 5h</option><option value=`valor3`>Período de 12h</option></select><br><br>";
    if(contador == 0)
    {
        document.getElementById("parte").style.display = "block";
        document.getElementById("parte1").style.display = "none";
        document.getElementById("parte2").style.display = "none";
        document.getElementById("botaoSubParte").style.display = "block";
        contador++;
    }
    else if(contador == 1)
    {
        document.getElementById("parte1").style.display = "block";
        document.getElementById("parte2").style.display = "none";
        contador++;
    }
    else if(contador == 2)
    {
        document.getElementById("parte2").style.display = "block";
        document.getElementById("botaoAddParte").style.display = "none";
    }
}

function removePartes()
{
    if(contador == 0)
    {
        document.getElementById("parte").style.display = "none";
        document.getElementById("botaoSubParte").style.display = "none";
        document.getElementById("botaoAddParte").style.display = "block";
    }
    else if(contador == 1)
    {
        document.getElementById("parte1").style.display = "none";
        document.getElementById("parte2").style.display = "none";
        contador--;
    }
    else if(contador == 2)
    {
        document.getElementById("parte2").style.display = "none";
        contador--;
    }
}

function enviarCurriculo()
{
  var nome = "";
  nome = document.getElementById("nome").value;
  var cpf = "";
  cpf = document.getElementById("cpf").value;
  var endereco = ""
  endereco = document.getElementById("endereco").value;
  var telefone = "";
  telefone = document.getElementById("telefone").value;
  var motivo = "";
  motivo = document.getElementById("motivo").value;

  url = "http://localhost:8079/Capuretta/CandidatoAPI?nome="+nome+"&cpf="+cpf+"&endereco="+endereco+"&telefone="+telefone+"&descricao="+motivo;
  console.log(url);
  var xhr = new XMLHttpRequest();
  xhr.onload = function() {
    if (xhr.status >= 200 && xhr.status < 300) {
      console.log('Success!', JSON.parse(xhr.responseText));
    }
    else {
      let error_message= "Error <br>" + xhr.statusText;
      console.log(error_message);
    }
    console.log("Pages Retrieved");
  };
  xhr.open('POST', url, false);
  xhr.send();
}

function estimar()
{
    var s = document.getElementById("s").options[document.getElementById("s").selectedIndex].value;
    var h = document.getElementById("h").options[document.getElementById("s").selectedIndex].value;
    if(s != "0" && h != "0")
    {
      orcuserID = 0;
      verifyUser(orcUser, orcEmail, orcTelefone);
      console.log("orcuserID" + orcuserID);
      if(orcuserID > 0)
      {
          criarBudget(orcuserID, "Estimativa feita através do site");
          getInsertedBudget();
          console.log("inserido:" + lastInserted);
          lerServicos();
          obterProdutos();
      }
    }
    else
    {
        alert("Por favor, selecione um serviço!");
    }
}

function criarBudget(usuarioid, observacao)
{
    url = "http://localhost:8079/Capuretta/BudgetAPI?usuarioid="+usuarioid+"&observacoes="+observacao;
    console.log(url);
    var xhr = new XMLHttpRequest();
    xhr.onload = function() {
      if (xhr.status >= 200 && xhr.status < 300) {
        console.log('Success!', JSON.parse(xhr.responseText));
      }
      else {
        let error_message= "Error <br>" + xhr.statusText;
        console.log(error_message);
      }
    };
    xhr.open('POST', url, false);
    xhr.send();
}

function getInsertedBudget()
{
  url = "http://localhost:8079/Capuretta/BudgetAPI?last=true";
  console.log(url);
  var xhr = new XMLHttpRequest();
  xhr.onload = function() {
    if (xhr.status >= 200 && xhr.status < 300) {
      console.log('Success!', JSON.parse(xhr.responseText));
      var response = JSON.parse(xhr.responseText);
      var existe = false;

      for(var item of response.Budget)
      {
        console.log(item);
        lastInserted = item.id
      }
    }
    else {
      let error_message= "Error <br>" + xhr.statusText;
      console.log(error_message);
    }
  };
  xhr.open('GET', url, false);
  xhr.send();
}

function lerServicos()
{
  url = "http://localhost:8079/Capuretta/ServicoAPI";
  console.log(url);
  var xhr = new XMLHttpRequest();
  xhr.onload = function() {
    if (xhr.status >= 200 && xhr.status < 300) {
      console.log('Success!', JSON.parse(xhr.responseText));
      var response = JSON.parse(xhr.responseText);
      var existe = false;

      for(var item of response.Servico)
      {
        console.log(item);
        let serv = new ServicoObj(item.id, item.descricao, item.precoHora);
        serviceList.push(serv);
      }
    }
    else {
      let error_message= "Error <br>" + xhr.statusText;
      console.log(error_message);
    }
  };
  xhr.open('GET', url, false);
  xhr.send();
}

function obterProdutos()
{
  var s = document.getElementById("s").options[document.getElementById("s").selectedIndex].value;
  var h = document.getElementById("h").options[document.getElementById("h").selectedIndex].value;

  var idServico = s;
  var idBudget = lastInserted;
  var qtdHoras = 0;
  var precoFinal = 0;

  if(h == "matutino" || h == "vespertino")
    qtdHoras = 5;
  else
    qtdHoras = 12;

  var options =  serviceList.filter(function(e) {
  	return e.id == s;
  });
  var p = options[0].precohora;
  precoFinal = qtdHoras * p;
  addToCart(idServico,idBudget,qtdHoras,precoFinal);

  if(document.getElementById("parte").style.display == "block")
  {
      let s = document.getElementById("s0").options[document.getElementById("s0").selectedIndex].value;
      let h = document.getElementById("h0").options[document.getElementById("h0").selectedIndex].value;

      let idServico = s;
      let idBudget = lastInserted;
      let qtdHoras = 0;
      let precoFinal = 0;

      if(h == "matutino" || h == "vespertino")
        qtdHoras = 5;
      else
        qtdHoras = 12;

      let options =  serviceList.filter(function(e) {
      	return e.id == s;
      });
      let p = options[0].precohora;
      precoFinal = qtdHoras * p;
      addToCart(idServico,idBudget,qtdHoras,precoFinal);
  }
  if(document.getElementById("parte1").style.display == "block")
  {
      let s = document.getElementById("s1").options[document.getElementById("s1").selectedIndex].value;
      let h = document.getElementById("h1").options[document.getElementById("h1").selectedIndex].value;

      let idServico = s;
      let idBudget = lastInserted;
      let qtdHoras = 0;
      let precoFinal = 0;

      if(h == "matutino" || h == "vespertino")
        qtdHoras = 5;
      else
        qtdHoras = 12;

      let options =  serviceList.filter(function(e) {
      	return e.id == s;
      });
      let p = options[0].precohora;
      precoFinal = qtdHoras * p;
      addToCart(idServico,idBudget,qtdHoras,precoFinal);
  }
  if(document.getElementById("parte2").style.display == "block")
  {
      let s = document.getElementById("s2").options[document.getElementById("s2").selectedIndex].value;
      let h = document.getElementById("h2").options[document.getElementById("h2").selectedIndex].value;

      let idServico = s;
      let idBudget = lastInserted;
      let qtdHoras = 0;
      let precoFinal = 0;

      if(h == "matutino" || h == "vespertino")
        qtdHoras = 5;
      else
        qtdHoras = 12;

      let options =  serviceList.filter(function(e) {
      	return e.id == s;
      });
      let p = options[0].precohora;
      precoFinal = qtdHoras * p;
      addToCart(idServico,idBudget,qtdHoras,precoFinal);
  }
}

function addToCart(idServico,idBudget,qtdHoras,precoFinal)
{
  url = "http://localhost:8079/Capuretta/ServicoBudgetAPI?idservico="+idServico+"&idbudget="+idBudget+"&qtdehoras="+qtdHoras+"&preco="+precoFinal;
  console.log(url);
  var xhr = new XMLHttpRequest();
  xhr.onload = function() {
    if (xhr.status >= 200 && xhr.status < 300) {
      console.log('Success!', JSON.parse(xhr.responseText));
    }
    else {
      let error_message= "Error <br>" + xhr.statusText;
      console.log(error_message);
    }
    console.log("Pages Retrieved");
  };
  xhr.open('POST', url, false);
  xhr.send();
}
