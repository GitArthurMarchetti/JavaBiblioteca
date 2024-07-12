"use client"
import {
    NavigationMenu,
    NavigationMenuContent,
    NavigationMenuIndicator,
    NavigationMenuItem,
    NavigationMenuLink,
    NavigationMenuList,
    NavigationMenuTrigger,
    NavigationMenuViewport,
} from "@/components/ui/navigation-menu"

import { useEffect, useState } from "react";
import { useNavigate } from "react-router";

export default function Nav() {

    const [isblibliotecario, setIsblibliotecario] = useState(false)
    const [logado, setLogado] = useState(true)
    const biblio = localStorage.getItem("isBiblio")
    const nome = localStorage.getItem("nomeuser")
    const navigate = useNavigate()
    useEffect(() => {
        if (biblio == "true") {
            setIsblibliotecario(true)
        } else {
            setIsblibliotecario(false)
        }
        if (localStorage.getItem("nomeuser") == null) {
            setLogado(false)
        } else {
            setLogado(true)
        }
    });

    function deletar() {
        localStorage.clear()
        navigate("/")
    }


    return (
        <>
            <div className="w-[100vw] flex flex-row justify-between">
                {isblibliotecario ? (
                    <>
                        <NavigationMenu className="w-[100vw] flex flex-row justify-between">
                            <h1>Biblioteca</h1>
                            <NavigationMenuItem>
                                <div className="flex md:order-2">
                                    {logado ? (
                                        <>
                                            <span>Bem vindo {nome}</span>
                                            <button onClick={deletar} >Sair</button>
                                        </>
                                    ) : (
                                        <>
                                            <button onClick={() => navigate("/login")} >Login</button>
                                        </>
                                    )}
                                </div>
                            </NavigationMenuItem>
                            <NavigationMenu >
                                <NavigationMenuLink href="/gerenciarEmprestimo">Gerenciar emprestimos</NavigationMenuLink>
                                <NavigationMenuLink href="/aluno">Alunos</NavigationMenuLink>
                                <NavigationMenuLink href="/cadastroLivro">Livros</NavigationMenuLink>
                            </NavigationMenu>
                        </NavigationMenu>
                    </>
                ) : (
                    <>
                        <NavigationMenu className="w-[100vw] flex flex-row justify-between">
                            <NavigationMenuItem>
                                <span>Biblioteca</span>
                            </NavigationMenuItem>
                            <NavigationMenuItem />
                            <div className="flex flex-row w-[20vw] justify-evenly">
                                {logado ? (
                                    <>
                                        <span>Bem vindo {nome}</span>
                                        <button onClick={deletar} >Sair</button>
                                    </>
                                ) : (
                                    <>
                                        <button onClick={() => navigate("/login")} className="bg-green-300" >Login</button>
                                    </>
                                )}
                                <NavigationMenuItem />
                            </div>
                            <NavigationMenu className="w-[900px] flex flex-row justify-between">
                                <NavigationMenuLink href="/meusEmprestimos" className="mr-10">Meus emprestimos</NavigationMenuLink>
                                <NavigationMenuLink href="/cadastroLivro">Livros</NavigationMenuLink>
                            </NavigationMenu>

                        </NavigationMenu>
                    </>
                )

                }
            </div>
        </>)

}