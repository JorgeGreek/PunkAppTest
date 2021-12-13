import com.example.data.repository.PunkRepository
import com.example.usecase.GetBeers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals

@RunWith(MockitoJUnitRunner::class)
class GetBeersTest {

    @Mock
    lateinit var punkRepository: PunkRepository

    lateinit var getBeers: GetBeers

    @Before
    fun setUp() {
        getBeers = GetBeers(punkRepository)
    }

    @Test
    fun `Confirm if getRemoteBeers gets beers from API`(): Unit = runBlocking {
        whenever(punkRepository.suspendBeers(5)).thenReturn(mockedResponse)
        val result = getBeers.invoke(5)
        assertEquals(result, mockedResponse)
    }

}