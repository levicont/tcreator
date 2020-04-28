package com.lvg.tcreator.services;

import com.lvg.tcreator.models.Statistic;

/**
 * Created by Victor Levchenko LVG Corp. on 21.04.2020.
 */
public interface StatisticService {
    Statistic loadFromFile(byte[] file);
}
