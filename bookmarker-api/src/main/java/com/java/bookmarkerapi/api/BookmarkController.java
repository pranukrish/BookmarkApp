package com.java.bookmarkerapi.api;

import com.java.bookmarkerapi.domain.Bookmark;
import com.java.bookmarkerapi.domain.BookmarkService;
import com.java.bookmarkerapi.domain.BookmarksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1")Integer page){
        return bookmarkService.getBookmarks(page);
    }
}
