/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.block;

import com.google.common.base.Objects;

public class BlockBuilderStatus
{
    public static final int DEFAULT_MAX_PAGE_SIZE_IN_BYTES = 1024 * 1024;
    public static final int DEFAULT_MAX_BLOCK_SIZE_IN_BYTES = 64 * 1024;

    private final int maxPageSizeInBytes;
    private final int maxBlockSizeInBytes;

    private boolean full;
    private int currentSize;

    public BlockBuilderStatus()
    {
        this(DEFAULT_MAX_PAGE_SIZE_IN_BYTES, DEFAULT_MAX_BLOCK_SIZE_IN_BYTES);
    }

    public BlockBuilderStatus(int maxPageSizeInBytes, int maxBlockSizeInBytes)
    {
        this.maxPageSizeInBytes = maxPageSizeInBytes;
        this.maxBlockSizeInBytes = maxBlockSizeInBytes;
    }

    public BlockBuilderStatus(BlockBuilderStatus blockBuilderStatus)
    {
        this.maxPageSizeInBytes = blockBuilderStatus.maxPageSizeInBytes;
        this.maxBlockSizeInBytes = blockBuilderStatus.maxBlockSizeInBytes;
    }

    public int getMaxBlockSizeInBytes()
    {
        return maxBlockSizeInBytes;
    }

    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    public boolean isFull()
    {
        return full || currentSize > maxPageSizeInBytes;
    }

    public void setFull()
    {
        this.full = true;
    }

    public void addBytes(int bytes)
    {
        currentSize += bytes;
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this)
                .add("maxSizeInBytes", maxPageSizeInBytes)
                .add("full", full)
                .add("currentSize", currentSize)
                .toString();
    }
}