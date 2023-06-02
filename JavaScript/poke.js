
const user = {
    name:"Jose Luis",
    direccion:"C/Antonio",
    hobibies:[
        "tennis", "lectura","series"
    ]
}
const amigos= [
    {
        name:"Ignacio", apelido:"Pereiro"
    },
    {
        name:"Alberto", apelido:"Nuñez"
    },
    {
        name:"Maria", apelido:"del Sol"
    }
    ,    {
        name:"Clara", apelido:"Campos"
    }
]
//Añadimos al usuario la constante amigos
user.amigos = amigos

let output = ''
for (let i = 0; i< amigos.length; i++) {
    output = output + '<li>+(amigos[i].name)}+(amigos[i].apelido)+</li>'
}

document.getElementById('friends').innerHTML = output

console.log(JSON.stringify(user))
