// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `Player.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package MP3Player;

public abstract class Callback_Player_getFileLength
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackInt
{
    public final void __completed(Ice.AsyncResult __result)
    {
        PlayerPrxHelper.__getFileLength_completed(this, __result);
    }
}