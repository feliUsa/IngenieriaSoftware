package com.example.proyectoveci

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TermsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        val terms: TextView = findViewById<TextView>(R.id.termsUwU)
        terms.text = "Términos y Condiciones de Uso de la Aplicación Hola veci!\n" +
                "\n" +
                "Fecha de última actualización: 9/2/2023\n" +
                "\n" +
                "Aceptación de los Términos\n" +
                "Al utilizar la aplicación Hola veci! (en adelante, \"la Aplicación\"), aceptas los siguientes términos y condiciones. Si no estás de acuerdo con estos términos, te rogamos que no utilices la Aplicación.\n" +
                "\n" +
                "Uso de la Aplicación\n" +
                "2.1. Licencia de Uso: Te concedemos una licencia limitada, no exclusiva y no transferible para utilizar la Aplicación de acuerdo con estos términos y para fines personales o comerciales.\n" +
                "\n" +
                "2.2. Requisitos de Usuario: Debes tener al menos [edad mínima] años para utilizar la Aplicación. Al utilizar la Aplicación, garantizas que cumples con este requisito de edad.\n" +
                "\n" +
                "2.3. Registro de Cuenta: Si la Aplicación requiere registro, eres responsable de proporcionar información precisa y mantener la seguridad de tu cuenta.\n" +
                "\n" +
                "Privacidad\n" +
                "3.1. Política de Privacidad: Nuestra política de privacidad, disponible en [enlace a la política de privacidad], describe cómo recopilamos, utilizamos y compartimos tus datos personales. Al utilizar la Aplicación, aceptas nuestra política de privacidad.\n" +
                "\n" +
                "3.2. Consentimiento para el Tratamiento de Datos: Al utilizar la Aplicación, consientes el tratamiento de tus datos personales de acuerdo con nuestra política de privacidad.\n" +
                "\n" +
                "Contenido de la Aplicación\n" +
                "4.1. Derechos de Propiedad: Todos los derechos de propiedad intelectual sobre la Aplicación y su contenido (incluidos, entre otros, derechos de autor, marcas comerciales y patentes) son propiedad exclusiva de [Nombre del Desarrollador] o sus licenciantes.\n" +
                "\n" +
                "4.2. Restricciones de Uso: No puedes copiar, modificar, distribuir, vender o realizar ingeniería inversa en la Aplicación ni en su contenido sin permiso previo por escrito de [Nombre del Desarrollador].\n" +
                "\n" +
                "Actualizaciones y Cambios\n" +
                "Nos reservamos el derecho de actualizar, modificar o cambiar la Aplicación y estos términos en cualquier momento. Te notificaremos sobre cambios significativos en los términos. Si continúas utilizando la Aplicación después de la notificación de cambios, se considerará que has aceptado los nuevos términos.\n" +
                "\n" +
                "Limitación de Responsabilidad\n" +
                "6.1. Uso bajo tu Propio Riesgo: Utilizas la Aplicación bajo tu propio riesgo. No nos hacemos responsables de ningún daño, pérdida o perjuicio que puedas sufrir como resultado de tu uso de la Aplicación.\n" +
                "\n" +
                "6.2. Sin Garantías: La Aplicación se proporciona \"tal cual\" y \"según disponibilidad\", sin garantías de ningún tipo, ya sean expresas o implícitas.\n" +
                "\n" +
                "Rescisión\n" +
                "Podemos rescindir tu acceso a la Aplicación en cualquier momento, por cualquier motivo, sin previo aviso.\n" +
                "\n" +
                "Ley Aplicable\n" +
                "Estos términos se rigen por las leyes del [país o estado] y cualquier disputa se someterá a la jurisdicción de los tribunales de [ciudad o lugar].\n" +
                "\n" +
                "Contacto\n" +
                "Si tienes alguna pregunta sobre estos términos o la Aplicación, puedes ponerte en contacto con nosotros en juan.serrano03@usa.edu.co."
        terms.setTextColor(Color.WHITE)
        terms.setTypeface(Typeface.MONOSPACE,Typeface.BOLD)
    }


}