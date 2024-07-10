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

import React from 'react';
// Importa o hook useNavigate do pacote react-router-dom
import { useNavigate } from 'react-router-dom';


export default function Nav() {
    const irPara = useNavigate();
    // Define a função handleClick que será chamada ao clicar no botão
    const handleClick = (path: string) => {
        // Navegar para a página Home ao clicar em algum botão
        irPara(path);
    };

    return (
        <>
            <section className='h-[100%] w-[100vw]'>
                <NavigationMenu>
                    <NavigationMenuList>
                        <NavigationMenuItem>
                            <button onClick={() => handleClick("/")}><NavigationMenuTrigger>Home</NavigationMenuTrigger></button>
                        </NavigationMenuItem>
                        <NavigationMenuItem>
                            <button onClick={() => handleClick("/Login")}><NavigationMenuTrigger>Login</NavigationMenuTrigger></button>
                        </NavigationMenuItem>
                        <NavigationMenuItem>
                            <button onClick={() => handleClick("/Cadastro")}><NavigationMenuTrigger>Cadastro</NavigationMenuTrigger></button>
                        </NavigationMenuItem>
                    </NavigationMenuList>
                </NavigationMenu>
            </section>
        </>
    );
}