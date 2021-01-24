package com.stealthmountain.javaplayground;

public class Container<ContainedType> {
    interface State<
            GoodStateType extends GoodState<
                    GoodStateType,
                    BadStateType,
                    GoodType,
                    ContainedType
                    >,
            BadStateType extends BadState<
                    GoodStateType,
                    BadStateType,
                    GoodType,
                    ContainedType
                    >,
            GoodType extends Good<ContainedType>,
            ContainedType
            > extends Either<
            GoodStateType,
            BadStateType,
            GoodType,
            Bad
            > {
    }

    static abstract class GoodState<
            GoodStateType extends GoodState<
                    GoodStateType,
                    BadStateType,
                    GoodType,
                    ContainedType
                    >,
            BadStateType extends BadState<
                    GoodStateType,
                    BadStateType,
                    GoodType,
                    ContainedType
                    >,
            GoodType extends Good<ContainedType>,
            ContainedType
            > extends Either.LeftImpl<
            GoodStateType,
            BadStateType,
            GoodType,
            Bad
            > implements State<
            GoodStateType,
            BadStateType,
            GoodType,
            ContainedType
            > {
        GoodState(
                Class<GoodStateType> selfClass,
                GoodType value
        ) {
            super(
                    selfClass,
                    value
            );
        }
    }

    static abstract class BadState<
            GoodStateType extends GoodState<
                    GoodStateType,
                    BadStateType,
                    GoodType,
                    ContainedType
                    >,
            BadStateType extends BadState<
                    GoodStateType,
                    BadStateType,
                    GoodType,
                    ContainedType
                    >,
            GoodType extends Good<ContainedType>,
            ContainedType
            > extends Either.RightImpl<
            GoodStateType,
            BadStateType,
            GoodType,
            Bad
            > implements State<
            GoodStateType,
            BadStateType,
            GoodType,
            ContainedType
            > {
        BadState(
                Class<BadStateType> selfClass,
                Bad value
        ) {
            super(
                    selfClass,
                    value
            );
        }
    }

    static abstract class Good<ContainedType> {
        final ContainedType contained;

        Good(ContainedType contained) {
            this.contained = contained;
        }
    }

    static final class Bad {
    }

    public Container(ContainedType contained) {
        final class Context {
            final class MyGoodState extends GoodState<
                    MyGoodState,
                    MyBadState,
                    MyGood,
                    ContainedType
                    > {
                MyGoodState(MyGood value) {
                    super(MyGoodState.class, value);
                }
            }

            final class MyBadState extends BadState<
                    MyGoodState,
                    MyBadState,
                    MyGood,
                    ContainedType
                    > {
                MyBadState(Bad value) {
                    super(MyBadState.class, value);
                }
            }

            final class MyGood extends Good<ContainedType> {
                MyGood(ContainedType contained) {
                    super(contained);
                }
            }
        }
    }
}
