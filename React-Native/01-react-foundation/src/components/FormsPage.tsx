import { useForm } from "react-hook-form"

type FormInputs = {
    email: string;
    password: string;
}

export const FormsPage = () => {

    const { register } = useForm<FormInputs>({
        defaultValues: {
            email: 'cesar@mail.com',
            password: 'Abc123*',
        }
    });

    return (
        <>
            <form>
                <h3>Formularios</h3>
                <div style={{display: 'flex', flexDirection: 'column'}}>
                    <input type="email" placeholder="Email" { ...register('email') } />
                    <input type="password" placeholder="Contraseña" { ...register('password') } />
                    <button type="submit" value="Enviar">Ingresar</button>
                </div>
            </form>
        </>
    )
}
