package com.solidtype.atenas_apk_2.authentication.actualizacion.data.remote_auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.core.remote.authtentication.MetodoAutenticacion
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloudImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.hamcrest.core.Every
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

class MetodoAutenticacionImplTest {
    @RelaxedMockK
    private lateinit var dataCloud: DataCloud
    @RelaxedMockK
    private lateinit var fireAuth: FirebaseAuth
    @RelaxedMockK
        private lateinit var authResolt:  AuthResult
    @RelaxedMockK
    private lateinit var firebaseUser: FirebaseUser

    private lateinit var metodoAuthenticacion: MetodoAutenticacion

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        metodoAuthenticacion = MetodoAutenticacionImpl(fireAuth, dataCloud)

    }
    @Test
    fun signing() = runTest {
            // Given
            val respuesta = CheckListAuth(
                autenticado = false,
                deviceRegistrado = false,
                licensiaActiva = false,
                tipoUser = TipoUser.UNKNOWN,
                emailUsuario = "adderlis@live.com"
            )
            // When
            every { firebaseUser.email } returns "adderlis@live.com"
            every  { authResolt.user } returns firebaseUser
            coEvery { dataCloud.autenticacionCloud("", "", "") }returns respuesta
            coEvery { fireAuth.signInWithEmailAndPassword("", "").await() } returns authResolt
            //Then
            //metodoAuthenticacion.signing("", "", "", "")
           //coVerify(exactly = 1) { fireAuth.signInWithEmailAndPassword("", "")}
        }

    @Test
    fun signout() {
    }

    @Test
    fun eliminarUsuario() {
    }

    @Test
    fun cambiarPassword() {
    }

    @Test
    fun olvideMiPassword() {
    }

    @Test
    fun registerNewUsers() {
    }

    @Test
    fun getUsuarioActual() {
    }
}