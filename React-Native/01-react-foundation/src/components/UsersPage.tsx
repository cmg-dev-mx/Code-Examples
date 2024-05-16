import axios from "axios"
import { useEffect } from "react"

export const UsersPage = () => {

    useEffect(() => {        
        // fetch('https://reqres.in/api/users')
        //     .then(resp => resp.json())
        //     .then(data => console.log(data));

        axios.get('https://reqres.in/api/users')
        .then(resp => console.log(resp.data));

    }, [])

    return (
        <>
            <h1>Usuarios:</h1>
            <table>
                <thead>
                    <tr>
                        <th>Avatar</th>
                        <th>Nombre</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>avatar</td>
                        <td>nombre</td>
                        <td>email</td>
                    </tr>
                </tbody>
            </table>
        </>
    )
}
