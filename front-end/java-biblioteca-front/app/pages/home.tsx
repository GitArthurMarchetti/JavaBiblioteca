"use client"
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Nav from "../components/nav";

export default function Home() {
    return (
        <>
            <Nav />

            <main className="h-[90vh] w-full  flex flex-col justify-center items-center ">
                <section>
                    <h1 className="text-red-600 font-bold text-6xl">Bem vindo a biblioteca!</h1>
                </section>
            </main>
        </>
    )
}