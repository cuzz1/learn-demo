package com.cuzz.byteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * @Author: cuzz
 * @Date: 2019/1/18 13:45
 * @Description:
 */
public class ByteBufTest03 {
    public static void main(String[] args) {
        // 新建一个复合 buffer
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf dirctBuf = Unpooled.directBuffer(8);

        compositeByteBuf.addComponent(heapBuf);
        compositeByteBuf.addComponent(dirctBuf);

        compositeByteBuf.forEach(System.out::println);
        // 输出
        // UnpooledSlicedByteBuf(ridx: 0, widx: 0, cap: 0/0, unwrapped: UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 0, cap: 10))
        // UnpooledSlicedByteBuf(ridx: 0, widx: 0, cap: 0/0, unwrapped: UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeNoCleanerDirectByteBuf(ridx: 0, widx: 0, cap: 8))
    }
}
