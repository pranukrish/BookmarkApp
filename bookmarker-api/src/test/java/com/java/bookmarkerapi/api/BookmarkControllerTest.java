package com.java.bookmarkerapi.api;

import com.java.bookmarkerapi.domain.Bookmark;
import com.java.bookmarkerapi.domain.BookmarkRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
public class BookmarkControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    BookmarkRepository bookmarkRepository;

    private List<Bookmark> bookmarks;


    @BeforeEach
    void setUp(){
        bookmarkRepository.deleteAllInBatch();
        bookmarks = new ArrayList<>();
        bookmarks.add(new Bookmark (null, "SivaLabs",  "https://sivalabs.in", Instant.now()));
        bookmarks.add(new Bookmark (null, "SpringBlog", "https://spring.io/blog", Instant.now()));
        bookmarks.add(new Bookmark (null, "Quarkus", "https://quarkus.io/", Instant.now()));
        bookmarks.add(new Bookmark (null, "Micronaut",  "https://micronaut.io/", Instant.now()));
        bookmarks.add(new Bookmark (null, "J00Q",  "https://www.jooq.org/", Instant.now()));
        bookmarks.add(new Bookmark (null, "VladMihalcea",  "https://vladmihalcea.com", Instant.now()));
        bookmarks.add(new Bookmark (null, "Thoughts On Java", "https://thorben-janssen.com/", Instant.now()));
        bookmarks.add(new Bookmark (null, "DZone",  "https://dzone.com/", Instant.now()));
        bookmarks.add(new Bookmark (null, "DevOpsBookmarks",  "http://www.devopsbookmarks.com/", Instant.now()));
        bookmarks.add(new Bookmark (null, "Kubernetes docs", "https://kubernetes.io/docs/home/", Instant.now()));
        bookmarks.add(new Bookmark (null, "Expressjs", "https://expressis.com/", Instant.now()));
        bookmarks.add(new Bookmark (null, "Marcobehler",  "https://www.marcobehler.com", Instant.now()));
        bookmarks.add(new Bookmark (null, "baeldung",  "https://www.baeldung.com", Instant.now()));
        bookmarks.add(new Bookmark (null, "devolan","https://www.devglan.com", Instant.now()));
        bookmarks.add(new Bookmark (null, "Linuxize",  "https://linuxize.com", Instant.now()));

        bookmarkRepository.saveAll(bookmarks);
    }
    @Test
    public void shouldGetBookmarks() throws Exception{
        mvc.perform(get("/api/bookmarks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(15)))
                .andExpect (jsonPath(  "$.totalPages", CoreMatchers.equalTo(  2)))
                .andExpect (jsonPath(  "$.currentPage", CoreMatchers.equalTo ( 1)))
                .andExpect (jsonPath( "$.isFirst", CoreMatchers.equalTo(  true)))
                .andExpect (jsonPath(  "$.isLast", CoreMatchers.equalTo(  false)))
                .andExpect (jsonPath(  "$.hasNext", CoreMatchers.equalTo(  true)))
                .andExpect (jsonPath( "$.hasPrevious", CoreMatchers.equalTo (false)));
    }
}