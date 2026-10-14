import React from 'react';

export const Input = ({ children, className = '' }: { children?: React.ReactNode, className?: string }) => {
  return <div className={`input ${className}`}>{children}</div>;
};
