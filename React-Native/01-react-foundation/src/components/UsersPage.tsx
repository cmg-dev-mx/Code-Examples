import { useUsers } from "../hooks/useUsers";
import { UserRow } from "./UserRow";

export const UsersPage = () => {

    const { users, nextPage, prevPage } = useUsers();

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
                    {
                        users.map(user => (
                            <UserRow key={user.id} user={user} />
                        ))
                    }

                </tbody>
            </table>

            <div>
                <button onClick={ prevPage }>Prev</button>
                <button onClick={ nextPage }>Next</button>
            </div>
        </>
    )
}
