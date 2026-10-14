package com.rightsflow.service;

import com.rightsflow.dto.LegalDto;
import com.rightsflow.entity.Bookmark;
import com.rightsflow.entity.Right;
import com.rightsflow.entity.User;
import com.rightsflow.exception.ResourceNotFoundException;
import com.rightsflow.repository.BookmarkRepository;
import com.rightsflow.repository.RightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarkService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookmarkService.class);

    public BookmarkService(BookmarkRepository bookmarkRepository, RightRepository rightRepository, RightService rightService) {
        this.bookmarkRepository = bookmarkRepository;
        this.rightRepository = rightRepository;
        this.rightService = rightService;
    }



    private final BookmarkRepository bookmarkRepository;
    private final RightRepository rightRepository;
    private final RightService rightService;

    @Transactional(readOnly = true)
    public List<LegalDto.RightSummaryDto> getUserBookmarks(Long userId) {
        return bookmarkRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(b -> rightService.mapToSummaryDto(b.getRight()))
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean toggleBookmark(Long rightId, User user) {
        Right right = rightRepository.findById(rightId)
                .orElseThrow(() -> new ResourceNotFoundException("Right", "id", rightId));

        if (bookmarkRepository.existsByUserIdAndRightId(user.getId(), rightId)) {
            bookmarkRepository.deleteByUserIdAndRightId(user.getId(), rightId);
            return false; // un-bookmarked
        } else {
            Bookmark b = Bookmark.builder().user(user).right(right).build();
            bookmarkRepository.save(b);
            return true; // bookmarked
        }
    }
}
