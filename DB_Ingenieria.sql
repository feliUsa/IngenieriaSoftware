create TABLE 'datosClientes'(
    'id' int(11) not null primary key,
    'nombre' varchar(30) not null,
    'correo' varchar(20) not null,
    'direccion' varchar(30),
    'telefono' varchar(13)
)
