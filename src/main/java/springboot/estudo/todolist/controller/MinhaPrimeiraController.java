package springboot.estudo.todolist.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeiraRota")
//http://localhost:8081/...
public class MinhaPrimeiraController {

    /**
     * Métodos de acesso do HTTP
     * GET - Buscar uma informaçao
     * POST - Adicionar um dado/informação
     * DELETE - Remover um dado
     * PUT - Aleterar um dado/informação
     * PATCH - Alterar somente uma parte do dado/informação
     */

    // Método (Funcionalidade) de uma classe
    @GetMapping("/primeiroMetodo")
    public String primeiraMensagem(){
        return "Funcionando";
    }
}
