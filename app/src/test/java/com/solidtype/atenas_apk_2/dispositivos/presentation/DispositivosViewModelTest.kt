package com.solidtype.atenas_apk_2.dispositivos.presentation




import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.base.Verify.verify
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.CasosDispositivo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DispositivosViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var casosDispositivo: CasosDispositivo

    @RelaxedMockK
    private lateinit var viewModel: DispositivosViewModel

    @RelaxedMockK
    private var restoreDeleted: Dispositivo? = null
    private val dispositivoSlot =   slot<Dispositivo>()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = DispositivosViewModel(casosDispositivo)
        Dispatchers.setMain(Dispatchers.Unconfined)

    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onEvent GetDevice calls getDispositivos`() = runTest {
        //Given
        val dispositivos = flow<List<Dispositivo>> { listOf( Dispositivo(
            id_dispositivo= 12222,
            nombre_comercial= "nomber0",
            modelo= "nombere0",
            marca= "nombere0",
            estado= true
        ))  }

        coEvery { casosDispositivo.getDispositivos() } returns dispositivos
        //When
        var listaDispositivos:List<Dispositivo> = emptyList<Dispositivo>()
        dispositivos.collect{
            listaDispositivos = it

        }
        viewModel.onEvent(DispositivosEvent.GetDevice)

        //Then
        coVerify(exactly = 1){casosDispositivo.getDispositivos()}
        assert(viewModel.uiState.value.dispositivos == listaDispositivos)

    }

    @Test
    fun `onEvent Delete calls deleteDevice and restores correctly`() = runTest {
        //Given
//        val dispositivo =  Dispositivo(
//            id_dispositivo= 12222,
//            nombre_comercial= "nomber0",
//            modelo= "nombere0",
//            marca= "nombere0",
//            estado= true
//        )
//        every { viewModel.onEvent(DispositivosEvent.Delete(dispositivo))
//
//        }answers {
//            restoreDeleted = dispositivo
//        }
//        //When
//        viewModel.onEvent(DispositivosEvent.Delete(dispositivo))
//
//        //Then
//        assert(restoreDeleted == null)


    }

    @Test
    fun `onEvent Search calls search`() = runTest {

    }

    @Test
    fun `onEvent UpdateDevice calls updateDispositivo`() = runTest{
        val dispositivo = Dispositivo(
            id_dispositivo= 13333,
            nombre_comercial= "nombere2",
            modelo= "nombere2",
            marca= "nombere2",
            estado= true
        )


    }

    @Test
    fun `onEvent AddDevice calls addDevice`() = runTest {
        val dispositivo =  Dispositivo(
            id_dispositivo= 12222,
            nombre_comercial= "nomber0",
            modelo= "nombere0",
            marca= "nombere0",
            estado= true
        )

    }

    @Test
    fun `onEvent RestoreOnDelete calls addDevice with restore value`() = runTest {
        val dispositivo = Dispositivo(
            id_dispositivo= 12222,
            nombre_comercial= "nomber0",
            modelo= "nombere0",
            marca= "nombere0",
            estado= true
        )
    }
}
