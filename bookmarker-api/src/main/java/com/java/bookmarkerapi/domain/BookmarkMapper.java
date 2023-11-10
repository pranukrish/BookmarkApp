package com.java.bookmarkerapi.domain;

import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {
    public BookmarkDTO toDTO(Bookmark bookmark){
        BookmarkDTO bookmarkDTO = new BookmarkDTO();

        bookmarkDTO.setId(bookmark.getId());
        bookmarkDTO.setUrl(bookmark.getUrl());
        bookmarkDTO.setTitle(bookmark.getTitle());
        bookmarkDTO.setCreatedAt(bookmark.getCreatedAt());

        return bookmarkDTO;
    }
}
