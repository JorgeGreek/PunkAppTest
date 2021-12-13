import com.example.data.repository.PunkRepository
import com.example.usecase.GetBeersById
import com.example.usecase.GetBeersByName
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetBeersByNameTest {

    @Mock
    lateinit var punkRepository: PunkRepository

    lateinit var getBeersByName: GetBeersByName


    @Before
    fun setUp() {
        getBeersByName = GetBeersByName(punkRepository)
    }

    @Test
    fun `Confirm if getDetailBeerByName retrieves a beer from the API`(): Unit =
        runBlocking {
            whenever(punkRepository.suspendSearchBeers("bu", 1)).thenReturn(mockedResponse)
            val result = getBeersByName.invoke("bu", 1)
            Assert.assertEquals(mockedResponse, result)
        }
}