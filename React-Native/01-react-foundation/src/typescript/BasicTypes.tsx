export const BasicTypes = () => {

    const name: string = 'Cesar';
    const age: number = 36;
    const isActive: boolean = true;

    const powers: string[] = ['React', 'Vue', 'Angular', 'Svelte', 'Ember', 'Backbone'];
    powers.push('jQuery');

    return (
        <>
            <h3>Tipos básicos</h3>

            {name} - {age} - {isActive ? 'Activo' : 'Inactivo'}
            <br />
            {powers.join(', ')}
        </>
    )
}
