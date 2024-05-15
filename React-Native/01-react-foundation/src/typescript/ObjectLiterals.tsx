interface Address {
    country: string;
    houseNumber: number;
}

interface Person {
    firstName: string;
    lastName: string;
    age: number;
    address: Address;
    isAlive?: boolean;
}

export const ObjectLiterals = () => {

    const person: Person = {
        age: 37,
        address: {
            country: 'USA',
            houseNumber: 123
        },
        firstName: "Cesar",
        lastName: "Morales",
        isAlive: undefined
    }

  return (
    <>
        <h3>Objetos literales</h3>
        <pre>
            {JSON.stringify(person, null, 2)}
        </pre>
    </>
  )
}
