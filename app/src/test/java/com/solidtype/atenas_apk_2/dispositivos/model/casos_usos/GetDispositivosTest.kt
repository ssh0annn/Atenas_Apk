package com.solidtype.atenas_apk_2.dispositivos.model.casos_usos

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.repository.DispositivosRepository
import io.mockk.MockKAdditionalAnswerScope
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetDispositivosTest {

    @RelaxedMockK
    private lateinit var repo: DispositivosRepository

    lateinit var getDispositivos: GetDispositivos

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getDispositivos = GetDispositivos(repo)
    }

    @Test
    fun ObtenerTodosLosDispositivosDeLaBaseDedatosBacios(): Unit =
        runBlocking { //Cuando usamos corrutinas

            //Given
            coEvery {
                repo.getDispositivos()
            } returns emptyFlow()

            //when
            var dispositivosCapturados: List<Dispositivo> = emptyList()
            getDispositivos().collect {
                dispositivosCapturados = it
            }


            //Then
            assert(dispositivosCapturados == emptyList<Dispositivo>())


        }
    @Test
    fun GetDispositivosDeBBDDCondatos(): Unit = runBlocking {
        val myList = flow<List<Dispositivo>> {
            listOf(
                Dispositivo(
                    id_dispositivo= 10000,
                    nombre_comercial= "nombere1",
                    modelo= "nombere1",
                    marca= "nombere1",
                    estado= true
                ),
                Dispositivo(
                    id_dispositivo= 12222,
                    nombre_comercial= "nomber0",
                    modelo= "nombere0",
                    marca= "nombere0",
                    estado= true
                ),
                Dispositivo(
                    id_dispositivo= 13333,
                    nombre_comercial= "nombere2",
                    modelo= "nombere2",
                    marca= "nombere2",
                    estado= true
                )
            )
        }
        //Given
        coEvery {
            repo.getDispositivos()
        } returns myList

        //Then
        var dispositivosCapturados = getDispositivos()


        //When
        assert(dispositivosCapturados == myList )

    }
}