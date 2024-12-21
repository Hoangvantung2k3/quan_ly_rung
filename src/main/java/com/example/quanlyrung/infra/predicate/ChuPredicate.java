package com.example.quanlyrung.infra.predicate;

import com.example.quanlyrung.infra.entity.QChu;
import com.querydsl.core.types.Predicate;

public class ChuPredicate {
    public static QChu chu = QChu.chu;

    public Predicate chuHasName(String name) {
        return chu.name.containsIgnoreCase(name);
    }
}
