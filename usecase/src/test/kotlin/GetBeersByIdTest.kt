import com.example.data.repository.PunkRepository
import com.example.usecase.GetBeersById
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetBeersByIdTest {

    @Mock
    lateinit var punkRepository: PunkRepository

    lateinit var getBeersById: GetBeersById


    @Before
    fun setUp() {
        getBeersById = GetBeersById(punkRepository)
    }

    @Test
    fun `Confirm if getDetailBeerById retrieves a beer from the API`(): Unit =
        runBlocking {
            whenever(punkRepository.suspendDetails(3)).thenReturn(mockedResponse)
            val result = getBeersById.invoke(3)
            Assert.assertEquals(mockedResponse, result)
        }

}