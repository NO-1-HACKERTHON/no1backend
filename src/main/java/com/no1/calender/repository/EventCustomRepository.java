package com.no1.calender.repository;

import com.no1.calender.domain.event.Event;
import com.no1.calender.domain.event.QEvent;
import com.no1.calender.domain.event.QEventHashTag;
import com.no1.calender.dto.search.SearchCondition;
import com.no1.calender.dto.search.SearchType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Supplier;

@Repository
@RequiredArgsConstructor
public class EventCustomRepository {

    public final JPAQueryFactory queryFactory;

    public List<Event> search(SearchCondition condition) {
        return queryFactory
                .selectFrom(QEvent.event)
                .where(isSearchable(condition.getType(), condition.getContent()))
                .fetch();
    }

    BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

    BooleanBuilder hashTag(String hashtag) {
        return nullSafeBuilder(() -> QEventHashTag.eventHashTag1.eventHashTag.contains(hashtag));
    }

    BooleanBuilder subject(String subject) {
        return nullSafeBuilder(() -> QEvent.event.eventName.contains(subject));
    }

    BooleanBuilder isSearchable(SearchType sType, String content) {
        if (sType == SearchType.HT) {
            return hashTag(content);
        }
        else if (sType == SearchType.SJ) {
            return subject(content);
        }
        else {
            return hashTag(content).or(subject(content));
        }
    }
}
