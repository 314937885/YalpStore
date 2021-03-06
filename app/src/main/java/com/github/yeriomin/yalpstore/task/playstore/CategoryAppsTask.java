/*
 * Yalp Store
 * Copyright (C) 2018 Sergey Yeriomin <yeriomin@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.github.yeriomin.yalpstore.task.playstore;

import com.github.yeriomin.playstoreapi.CategoryAppsIterator;
import com.github.yeriomin.playstoreapi.GooglePlayAPI;
import com.github.yeriomin.yalpstore.AppListIterator;
import com.github.yeriomin.yalpstore.PlayStoreApiAuthenticator;

import java.io.IOException;

public class CategoryAppsTask extends EndlessScrollTask implements CloneableTask {

    private String categoryId;

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryAppsTask(AppListIterator iterator) {
        super(iterator);
    }

    @Override
    public CloneableTask clone() {
        CategoryAppsTask task = new CategoryAppsTask(iterator);
        task.setFilter(filter);
        task.setCategoryId(categoryId);
        task.setErrorView(errorView);
        task.setContext(context);
        task.setProgressIndicator(progressIndicator);
        return task;
    }

    @Override
    protected AppListIterator initIterator() throws IOException {
        return new AppListIterator(new CategoryAppsIterator(
            new PlayStoreApiAuthenticator(context).getApi(),
            categoryId,
            GooglePlayAPI.SUBCATEGORY.TOP_FREE
        ));
    }
}
